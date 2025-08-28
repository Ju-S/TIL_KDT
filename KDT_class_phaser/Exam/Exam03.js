class Exam03 extends Phaser.Scene {
    constructor() {
        super({key: 'Exam03'});
    }

    preload(){
        this.load.image('background', 'Assets/background2.png');
    }

    create() {
        let background = this.add
            .tileSprite(0, 0, 2560, 1420, 'background')
            .setOrigin(0, 0);

        this.cursor = this.input.keyboard.createCursorKeys();

        this.physics.world.setBounds(0, 0, background.width, background.height);

        this.me = this.physics.add.sprite(500, 500, 50, 50);
        this.speed = 300;

        this.me.setCollideWorldBounds(true);
        this.cameras.main.setBounds(0, 0, background.width, background.height);
        this.cameras.main.startFollow(this.me);
    }

    update() {
        this.playerMove();
    }

    playerMove() {
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

        if (this.cursor.up.isDown) {
            this.me.setVelocityY(-this.speed);
        }
        if (this.cursor.down.isDown) {
            this.me.setVelocityY(this.speed);
        }
        if ((this.cursor.up.isDown && this.cursor.down.isDown) ||
            (this.cursor.up.isUp && this.cursor.down.isUp)) {
            this.me.setVelocityY(0);
        }
    }
}