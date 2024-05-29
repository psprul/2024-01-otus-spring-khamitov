package ru.otus.hw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.service.TestRunnerServiceImpl;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final TestRunnerServiceImpl testRunnerService;

    @ShellMethod(value = "Start process for testing", key = {"s"})
    public void startTesting() throws Exception {
        testRunnerService.run();
    }
}
