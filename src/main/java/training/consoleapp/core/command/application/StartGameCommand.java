/**
 * Copyright (c) 2015, Patryk Roszczyniała
 */
package training.consoleapp.core.command.application;

import training.consoleapp.core.command.ConsoleCommand;
import training.consoleapp.core.io.MessageInput;
import training.consoleapp.core.io.MessageOutput;
import training.core.BoardService;
import training.core.gameservice.GameService;
import training.core.model.Board;
import training.core.model.Game;

/**
 * The Class StartGameCommand.
 *
 * @author Patryk Roszczyniala (p.roszczyniala@gmail.com)
 * @version $Id$
 */
public class StartGameCommand implements ConsoleCommand {

    /**
     * The game service.
     */
    private GameService gameService;

    /**
     * The console board service.
     */
    private BoardService consoleBoardService;

    /**
     * The message input.
     */
    private MessageInput messageInput;

    /**
     * The message output.
     */
    private MessageOutput messageOutput;

    /**
     * Instantiates a new start game command.
     *
     * @param gameService the game service
     * @param consoleBoardService the console board service
     * @param messageInput the message input
     * @param messageOutput the message output
     */
    public StartGameCommand(GameService gameService,
            BoardService consoleBoardService,
            MessageInput messageInput, MessageOutput messageOutput) {
        this.gameService = gameService;
        this.consoleBoardService = consoleBoardService;
        this.messageInput = messageInput;
        this.messageOutput = messageOutput;
    }

    public void run() {
        gameService.start(new Game(new Board()));
        messageOutput.show("msg_game_started");
        messageOutput.show("msg_next_player_move", gameService.getGame()
                .getActivePlayer().toString(),
                consoleBoardService.asString(gameService.getGame().getBoard()));
        messageInput.show("msg_enter_directions");
    }

}
