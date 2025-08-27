class GameOver extends Phaser.Scene {

    constructor() {
        super({key: 'GameOver'});
    }

    preload() {
    }

    create() {
        this.add.text(this.cameras.main.centerX, this.cameras.main.centerY, '😂GAME😅OVER😂', {
            fontSize: '100px',
            color: '#ff0000',
            align: 'center',
        }).setOrigin(0.5);
        let restartButton = this.add.text(this.cameras.main.centerX, this.cameras.main.centerY + 100, '다시?', {
            fontSize: '50px',
            color: '#888888',
            align: 'center',
            padding: {left:10, right:10, top:10, bottom:10}
        }).setOrigin(0.5).setInteractive();

        restartButton.on("pointerout", () => {
            this.game.canvas.style.cursor = 'default';
            restartButton.setColor('#888888');
            restartButton.setBackgroundColor('#000000');
        });
        restartButton.on("pointerover", () => {
            this.game.canvas.style.cursor = 'pointer';
            restartButton.setColor('#ffffff');
            restartButton.setBackgroundColor('#636363');
        });
        restartButton.on("pointerdown", () => {
            this.scene.start('Exam02');
        });
    }

    update() {
    }
}