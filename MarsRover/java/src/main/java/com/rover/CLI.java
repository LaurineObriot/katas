package com.rover;

import org.apache.commons.cli.*;

public class CLI {

    private static Options configParameters() {

        Option positionX = Option.builder("x")
                .desc("initial X landing rover position")
                .longOpt("positionX")
                .hasArg()
                .type(Integer.class)
                .required(true)
                .build();

        Option positionY = Option.builder("y")
                .desc("initial Y landing rover position")
                .longOpt("positionY")
                .hasArg()
                .type(Integer.class)
                .required(true)
                .build();

        Option direction = Option.builder("d")
                .desc("initial landing rover direction")
                .longOpt("direction")
                .hasArg()
                .type(String.class)
                .required(true)
                .build();

        Option commands = Option.builder("c")
                .desc("provide an array of commands for the rover to execute")
                .longOpt("commands")
                .hasArgs()
                .type(String[].class)
                .required(true)
                .build();

        final Options options = new Options();

        options.addOption(positionX);
        options.addOption(positionY);
        options.addOption(direction);
        options.addOption(commands);

        return options;
    }

    public static void main(String[] args) {
        Console console = new Console();

        console.print("~~~~~ ROVER INITIALIZATION ~~~~~\n");

        final Options options = configParameters();

        CommandLineParser parser = new DefaultParser();

        try {

            final CommandLine line;
            line = parser.parse(options, args);

            int positionX = Integer.parseInt(line.getOptionValue("x"));
            int positionY = Integer.parseInt(line.getOptionValue("y"));
            char direction = line.getOptionValue("d").charAt(0);
            String[] commands = line.getOptionValues("c");

            Position initialPosition = new Position(positionX, positionY, direction);

            Rover rover = new Rover(initialPosition, console);

            rover.run(commands);

        } catch (ParseException e) {

            console.print(e.getMessage() + "\n");

            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar target/rover-1.0-jar-with-dependencies.jar", options);
        }

        console.print("~~~~~ ROVER TERMINATED ~~~~~\n");
    }
}
