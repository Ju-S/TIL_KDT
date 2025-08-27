class MainScene extends Phaser.Scene {
    constructor() {
        super({key: "MainScene"});
    }

    preload() {

    }

    create() {
        this.cameras.main.setBackgroundColor("#FFFFFF");


        this.me = this.physics.add.sprite(100, 100, 50, 50);

        this.me.setMass(25);

        this.box = this.physics.add.sprite(250, 250, 50, 50);
        this.box.setImmovable(true);
        this.box.setBounce(1);
        this.box.setMass(50);
        this.box.setDrag(100);

        this.physics.add.collider(this.me, this.box, function(me, box) {
            console.log("box와 충돌");
        });

        this.cursor = this.input.keyboard.createCursorKeys();

        this.physics.world.setBounds(0, 0, 1000, 800);
        this.me.setCollideWorldBounds(true);
        this.box.setCollideWorldBounds(true);
        this.physics.world.fixedStep = true;

        this.speed = 200;
    }

    update() {
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