package org.example.Handler.input;

import org.example.Game.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private GamePanel gamePanel;
    private boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed;
    private boolean oPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
            playState(code);
        } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
            pauseState(code);
        } else if (gamePanel.getGameState() == gamePanel.getDialogState()) {
            dialogState(code);
        } else if (gamePanel.getGameState() == gamePanel.getCharacterState()) {
            characterState(code);
        } else if (gamePanel.getGameState() == gamePanel.getEndState()) {
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
        if (gamePanel.getGameState() == gamePanel.getPlayState()) {
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
                gamePanel.setGameState(gamePanel.getCharacterState());
            }
            pauseState(code);
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        if (code == KeyEvent.VK_P) {
            if (gamePanel.getGameState() == gamePanel.getPlayState()) {
                gamePanel.setGameState(gamePanel.getPauseState());
                gamePanel.stopMusic();
            } else if (gamePanel.getGameState() == gamePanel.getPauseState()) {
                gamePanel.setGameState(gamePanel.getPlayState());
                gamePanel.playMusic(0);
            }
        }
    }

    public void dialogState(int code) {
        if (gamePanel.getGameState() == gamePanel.getDialogState()) {
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.setGameState(gamePanel.getPlayState());
            }
        }
    }

    public void characterState(int code) {

        if (code == KeyEvent.VK_C) {
            gamePanel.setGameState(gamePanel.getPlayState());
        }
        // moving cursor inventory
        if (code == KeyEvent.VK_W) {
            if (gamePanel.getUserInterface().slotRow != 0) {
                gamePanel.getUserInterface().slotRow--;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gamePanel.getUserInterface().slotCol != 0) {
                gamePanel.getUserInterface().slotCol--;
            }
        }
        if (code == KeyEvent.VK_S) {
            if (gamePanel.getUserInterface().slotRow != 3) {
                gamePanel.getUserInterface().slotRow++;
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gamePanel.getUserInterface().slotCol != 4) {
                gamePanel.getUserInterface().slotCol++;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.getPlayer().setOrConsumeItemFromInventory();
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public void setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
    }

    public boolean isoPressed() {
        return oPressed;
    }

    public void setoPressed(boolean oPressed) {
        this.oPressed = oPressed;
    }
}
