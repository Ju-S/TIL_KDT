class Quiz01 extends Phaser.Scene {
    constructor() {
        super({key: 'Quiz01'});
    }

    preload() {
        this.load.image('background', 'Assets/sideScrollingBackground.jpg');
        this.load.spritesheet('player', 'Assets/player.png', {
            frameWidth: 128,
            frameHeight: 128
        })
    }

    create() {
        this.background = this.add.tileSprite(this.cameras.main.centerX, this.cameras.main.centerY, 1000, 360, 'background');
        this.me = this.physics.add.sprite(100, 200, 'player');
        this.me.setData('jumpCnt', 2);

        this.me.setSize(20, 100);
        this.me.setOffset(57, 20);

        this.ground = this.add.rectangle(0, 295, this.cameras.main.width, 10).setOrigin(0);
        let ground = this.ground;
        this.physics.add.existing(ground, true);

        this.cursor = this.input.keyboard.createCursorKeys();

        this.anims.create({
            key: 'run',
            frames: this.anims.generateFrameNumbers('player', {start: 0, end: 7}),
            frameRate: 6,
            repeat: -1,
        });

        this.me.play('run');

        this.physics.add.collider(ground, this.me);
    }

    update() {
        this.background.tilePositionX += 3;
        this.playerMove();
    }

    playerMove() {
        console.log(this.me.getData('jumpCnt'));
        const speed = 300;

        if (this.cursor.left.isDown) {
            this.me.setVelocityX(-speed);
        }
        if (this.cursor.right.isDown) {
            this.me.setVelocityX(speed);
        }
        if ((this.cursor.left.isDown && this.cursor.right.isDown) ||
            (this.cursor.left.isUp && this.cursor.right.isUp)) {
            this.me.setVelocityX(0);
        }

        if (this.me.body.onFloor()) {
            this.me.setData('jumpCnt', 2);
        }

        if (Phaser.Input.Keyboard.JustDown(this.cursor.space) && this.me.getData('jumpCnt') > 0) {
            this.me.setVelocityY(-400);
            this.me.setData('jumpCnt', this.me.getData('jumpCnt') - 1);
        }
    }
}