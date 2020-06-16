/*package command.commands;

import command.Command;
import exchange.Request;
import exchange.Response;
import io.Input;
import runner.Editor;
import runner.Executor;
import runner.ExecutorException;

import java.io.*;
import java.net.URL;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public final class ExecuteScriptCommand implements Command {
    @Override
    public Response execute(Editor editor) throws ExecutorException, FileNotFoundException {
        /*List<String> response = new ArrayList<>();
        if (editor.getValue() == null) {
            throw new ExecutorException("Требуется параметр: имя файла");
        }

        BufferedReader br = new BufferedReader(new FileReader(editor.getValue().trim()));
        String line;


            try {
                while (true) {
                    if (!((line = br.readLine()) != null)) break;
                    executeCommand(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        Executor executor = new Executor();
        editor.setIn(in);
        executor.setEditor(editor);
        editor.setFromFile(true);
        for (String command = in.readLine(); command != null; command = in.readLine()) {
            editor.setFromFile(true);
            response.addAll(executor.executeCommand(new Request(command)).getResponse());
        }
        editor.setFromFile(false);*/
        /*return new Response(true, new Response());
    }
}*/