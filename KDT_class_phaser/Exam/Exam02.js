class Exam02 extends Phaser.Scene {
    constructor() {
        super({key: "Exam02"});
    }

    preload() {
        // this.load.spritesheet('poop', 'Assets/poop.png',{
        //     frameWidth: 32,
        //     frameHeight: 32
        // });

        this.load.image('poop', 'Assets/poop.png');
        this.load.image('player', 'Assets/people.png');
        this.load.image('background', 'Assets/background.avif');
    }

    create() {
        this.cameras.main.setBackgroundColor("#FFFFFF");

        this.background = this.add.tileSprite(0, 0, 1000, 800, 'background');
        this.background.setOrigin(0, 0);

        this.boxes = [];
        // this.physics.add.collider(this.boxes);

        this.cnt = 0;

        this.speed = 400;

        this.timer = 0;

        let cameraWidth = this.cameras.main.width;
        let cameraHeight = this.cameras.main.height;

        this.physics.world.setBounds(0, 0, 1000, 800);

        this.me = this.physics.add.sprite(cameraWidth/2, cameraHeight-15, 'player');
        this.me.setCollideWorldBounds(true);
        this.me.setSize(15, 30);
        this.cursor = this.input.keyboard.createCursorKeys();

        this.deleteTrigger = this.add.rectangle(0, cameraHeight + 50, cameraWidth, 10, "#000000");
        this.deleteTrigger.setOrigin(0, 0);

        let bound = this.deleteTrigger;
        this.physics.add.existing(bound, true);

        this.physics.add.collider(this.boxes, bound, (box, bound)=> {
            box.destroy();
            this.boxes.splice(this.boxes.indexOf(box), 1);
        });

        // this.physics.add.collider(this.boxes, this.me, (box, bound) => {
        //     box.destroy();
        //     this.boxes.splice(this.boxes.indexOf(box), 1);
        //     // alert("게임오버");
        // });

        this.physics.add.overlap(this.boxes, this.me, (box, me) => {
            this.scene.start('GameOver');
        });
    }

    update(time) {
        if (this.cnt++ >= 10) {
            for (let i = 0; i < 2; i++) {
                this.box = this.physics.add.sprite((Math.random() * 1000) + 1, 0, 'poop');
                this.box.setOrigin(0, 0);
                this.box.setVelocityY((Math.random() * 400) + 300);
                this.box.setScale();
                // this.box.setCollideWorldBounds(true);
                this.boxes.push(this.box);
            }
            this.cnt = 0;
        }

        this.background.tilePositionY -= 3;

        this.physics.world.setBounds(0, 0, 1000, 800);

        if (this.cursor.left.isDown) {
            this.me.setVelocityX(-this.speed);
        }
        if (this.cursor.right.isDown) {
            this.me.setVelocityX(this.speed);
        }
        if ((this.cursor.left.isDown && this.cursor.right.isDown) ||
            (this.cursor.left.isUp && this.cursor.right.isUp)) {
            this.me.setVelocityX(0);
        }

        $("#time").html((time/1000).toFixed(2));
    }
}