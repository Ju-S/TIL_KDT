class MainScene extends Phaser.Scene {
    constructor(data) {
        super({ key: "MainScene" });
    }

    init(data) {
        this.startTime = data.startTime || 0;
    }

    preload() {
        this.load.spritesheet('arrow', 'assets/arrow.png', {
            frameWidth: 512,
            frameHeight: 107
        });

        this.load.spritesheet('player', 'assets/dumpling.png', {
            frameWidth: 64,
            frameHeight: 64
        });
    }

    create() {
        const width = this.game.config.width;
        const height = this.game.config.height;

        this.circleCenter = new Phaser.Math.Vector2(width / 2, height / 2);
        this.circleRadius = Math.min(width, height) / 2 - 10; // 원형 영역 반지름

        this.cameras.main.setBackgroundColor("#86ba5f");

        // 디버그용 원 시각화
        const graphics = this.add.graphics();
        graphics.lineStyle(4, 0xcccccc, 1);
        graphics.strokeCircle(this.circleCenter.x, this.circleCenter.y, this.circleRadius);

        this.score = this.add.text(width / 2, height / 2, '0', {
            fontSize: '80px',
            color: '#717171',
            align: 'center'
        });
        this.score.setOrigin(0.5, 0.5);

        // 플레이어
        this.me = this.physics.add.sprite(this.circleCenter.x, this.circleCenter.y, 'player');
        this.me.setScale(0.3);
        this.me.setCollideWorldBounds(false); // 우리가 직접 경계 처리할 거야

        // 입력
        this.cursor = this.input.keyboard.createCursorKeys();

        // 화살 그룹
        this.arrows = this.physics.add.group();

        // 충돌 처리
        this.physics.add.collider(this.me, this.arrows, () => {
            alert("게임 오버!");
            this.scene.start("MainScene", { startTime: this.time.now });
            location.reload();
        });

        // 속도 및 타이머 초기화
        this.speed = 300;
        this.arrowSpeed = 300;
        this.arrowInterval = 1000;
        this.circularInterval = 5000;

        // 랜덤 화살
        this.spawnTimer = this.time.addEvent({
            delay: this.arrowInterval,
            callback: this.spawnArrow,
            callbackScope: this,
            loop: true
        });

        // 원형 패턴 화살
        this.circularPatternTimer = this.time.addEvent({
            delay: this.circularInterval,
            callback: this.spawnCircularPattern,
            callbackScope: this,
            loop: true
        });

        // 난이도 증가
        this.difficultyTimer = this.time.addEvent({
            delay: 5000,
            callback: () => {
                this.arrowSpeed += 50;
                this.arrowInterval = Math.max(200, this.arrowInterval - 100);
                this.circularInterval = Math.max(1000, this.circularInterval - 500);

                this.spawnTimer.reset({
                    delay: this.arrowInterval,
                    callback: this.spawnArrow,
                    callbackScope: this,
                    loop: true
                });

                this.circularPatternTimer.reset({
                    delay: this.circularInterval,
                    callback: this.spawnCircularPattern,
                    callbackScope: this,
                    loop: true
                });
            },
            loop: true
        });
    }

    // time이 탭을 비활성화 했을 때 흐른다.
    // 화살은 생성이 안되어 게임오버는 되지 않고 점수만 올라감.
    update(time) {
        const elapsedTime = time - this.startTime;
        this.score.setText(Math.floor(elapsedTime / 10));

        const me = this.me;
        const cursors = this.cursor;

        me.setVelocity(0);

        if (cursors.left.isDown) {
            me.setVelocityX(-this.speed);
        } else if (cursors.right.isDown) {
            me.setVelocityX(this.speed);
        }

        if (cursors.up.isDown) {
            me.setVelocityY(-this.speed);
        } else if (cursors.down.isDown) {
            me.setVelocityY(this.speed);
        }

        // 원형 영역 밖으로 못 나가게
        const distFromCenter = Phaser.Math.Distance.Between(me.x, me.y, this.circleCenter.x, this.circleCenter.y);
        if (distFromCenter > this.circleRadius) {
            const dir = new Phaser.Math.Vector2(me.x - this.circleCenter.x, me.y - this.circleCenter.y).normalize();
            me.x = this.circleCenter.x + dir.x * this.circleRadius;
            me.y = this.circleCenter.y + dir.y * this.circleRadius;
        }
    }

    spawnArrow() {
        const centerX = this.me.x;
        const centerY = this.me.y;
        const edge = Phaser.Math.Between(0, 3);
        const margin = 50;

        let x, y;
        switch (edge) {
            case 0: // top
                x = Phaser.Math.Between(0, this.game.config.width);
                y = -margin;
                break;
            case 1: // right
                x = this.game.config.width + margin;
                y = Phaser.Math.Between(0, this.game.config.height);
                break;
            case 2: // bottom
                x = Phaser.Math.Between(0, this.game.config.width);
                y = this.game.config.height + margin;
                break;
            case 3: // left
                x = -margin;
                y = Phaser.Math.Between(0, this.game.config.height);
                break;
        }

        const arrow = this.arrows.create(x, y, 'arrow');
        arrow.setScale(0.05);
        arrow.setSize(5/0.05, 5/0.05);

        const direction = new Phaser.Math.Vector2(centerX - x, centerY - y).normalize();
        arrow.body.velocity.x = direction.x * this.arrowSpeed;
        arrow.body.velocity.y = direction.y * this.arrowSpeed;
        arrow.rotation = direction.angle();
    }

    spawnCircularPattern() {
        const playerX = this.me.x;
        const playerY = this.me.y;

        const arrowSpeed = 200;

        const numDirections = 8;
        const angleStep = Phaser.Math.DegToRad(360 / numDirections);
        const spawnDistance = this.circleRadius + 10;

        for (let i = 0; i < numDirections; i++) {
            const angle = angleStep * i;
            const dir = new Phaser.Math.Vector2(Math.cos(angle), Math.sin(angle));

            // 원의 경계에 위치
            const x = this.circleCenter.x + dir.x * spawnDistance;
            const y = this.circleCenter.y + dir.y * spawnDistance;

            const arrow = this.arrows.create(x, y, 'arrow');
            arrow.setScale(0.05);
            arrow.setSize(5/0.05, 5/0.05);
            arrow.setCollideWorldBounds(false);

            // 방향은 "플레이어 쪽"
            const directionToPlayer = new Phaser.Math.Vector2(playerX - x, playerY - y).normalize();
            arrow.body.velocity.x = directionToPlayer.x * arrowSpeed;
            arrow.body.velocity.y = directionToPlayer.y * arrowSpeed;
            arrow.rotation = directionToPlayer.angle();
        }
    }
}
