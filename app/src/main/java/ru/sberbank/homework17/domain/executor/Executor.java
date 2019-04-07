package ru.sberbank.homework17.domain.executor;

import ru.sberbank.homework17.domain.usecase.base.AbstractUseCase;

/**
 * This executor is responsible for running interactors on background threads.
 * <p/>
 */
public interface Executor {

    /**
     * This method should call the interactor's run method and thus start the interactor. This should be called
     * on a background thread as interactors might do lengthy operations.
     *
     * @param interactor The interactor to run.
     */
    void execute(final AbstractUseCase interactor);
}
