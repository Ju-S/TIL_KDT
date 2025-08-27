class Quiz01 extends Phaser.Scene {
    constructor() {
        super({key: 'Quiz01'});
    }

    preload() {
        this.load.image('background', 'Assets/sideScrollingBackground.jpg');
    }

    create() {
        this.background = this.add.tileSprite(this.cameras.main.centerX, this.cameras.main.centerY, 1000, 360, 'background');
    }

    update() {
        this.background.tilePositionX += 3;
    }
}