class Exam01 extends Phaser.Scene {
    constructor() {
        super({key: "Exam01"});
    }

    preload() {

    }

    create() {
        this.cameras.main.setBackgroundColor("#FFFFFF");

        this.boxes = [];

        for(let i = 0; i < 10; i++) {
            this.boxes.push(this.physics.add.sprite(i * 100 + 50, 20, 50, 50));
        }

        this.speed = 300;

        this.physics.world.setBounds(0, 0, 1000, 800);
        for (let box of this.boxes) {
            box.setCollideWorldBounds(true);
            box.setMass(Math.random() * 100 + 20);
            box.setBounce(1);
        }
        this.physics.add.collider(this.boxes);
    }

    update() {

    }
}