package org.example.Handler.input;

import org.example.Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
    public boolean oPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gamePanel.gameState == gamePanel.playState) {
            playState(code);
        } else if (gamePanel.gameState == gamePanel.pauseState) {
            pauseState(code);
        } else if (gamePanel.gameState == gamePanel.dialogState) {
            dialogState(code);
        } else if (gamePanel.gameState == gamePanel.characterState) {
            characterState(code);
        } else if (gamePanel.gameState == gamePanel.endState) {
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_O) {
            oPressed = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = false;
        }
    }

    public void playState(int code) {
        if (gamePanel.gameState == gamePanel.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_ENTER) {
                enterPressed = true;
            }
            if (code == KeyEvent.VK_O) {
                oPressed = true;
            }
            if (code == KeyEvent.VK_C) {
                gamePanel.gameState = gamePanel.characterState;
            }
            pauseState(code);
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (code == KeyEvent.VK_P) {
            if (gamePanel.gameState == gamePanel.playState) {
                gamePanel.gameState = gamePanel.pauseState;
                gamePanel.stopMusic();
            } else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.playState;
                gamePanel.playMusic(9);
            }
        }
    }

    public void dialogState(int code) {
        if (gamePanel.gameState == gamePanel.dialogState) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.gameState = gamePanel.playState;
            }
        }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_C) {
            gamePanel.gameState = gamePanel.playState;
        }
        // moving cursor inventory
        if (code == KeyEvent.VK_W) {
            if (gamePanel.UI.slotRow != 0) {
                gamePanel.UI.slotRow--;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gamePanel.UI.slotCol != 0) {
                gamePanel.UI.slotCol--;
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gamePanel.UI.slotRow != 3) {
                gamePanel.UI.slotRow++;
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gamePanel.UI.slotCol != 4) {
                gamePanel.UI.slotCol++;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.player.setOrConsumeItemFromInventory();
        }
    }
}
