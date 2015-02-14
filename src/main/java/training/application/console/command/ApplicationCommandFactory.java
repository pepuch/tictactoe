package training.application.console.command;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import training.application.console.io.MessageInput;
import training.application.console.io.MessageOutput;
import training.core.model.Game;

public final class ApplicationCommandFactory {

	private ApplicationCommandFactory() {
		// NOP
	}
	
	public static void run(String command, Game game, MessageOutput messageOutput, MessageInput messageInput) throws IOException {
		if (command.equals("start") || command.equals("y")) {
			new StartGameCommand().run(game, messageOutput, messageInput);
		} else if (game.hasStarted() && CommandPattern.USER_DIRECTIONS.matches(command)) {
			Integer x = Integer.parseInt(CommandPattern.USER_DIRECTIONS.group(1));
			Integer y = Integer.parseInt(CommandPattern.USER_DIRECTIONS.group(2));
			new UserDirectionsCommand().setDirections(x, y).run(game, messageOutput, messageInput);
		} else {
			messageOutput.show("wrn_unknown_command");
			messageInput.print(messageInput.getLastMessage());
		}
	}

	private enum CommandPattern {
		USER_DIRECTIONS("(\\d),(\\d)");
		
		private String pattern;
		private Matcher m;
		
		private CommandPattern(String pattern) {
			this.pattern = pattern;
		}
		
		public boolean matches(String command) {
			Pattern p = Pattern.compile(pattern);
			m = p.matcher(command);
			return m.matches();
		}
		
		public String group(int  group) {
			return m.group(group);
		}
		
	}
	
}