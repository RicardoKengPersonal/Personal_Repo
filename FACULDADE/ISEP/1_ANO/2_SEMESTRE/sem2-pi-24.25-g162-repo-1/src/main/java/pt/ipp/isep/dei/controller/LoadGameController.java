package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.domain.game.CurrentGame;
import pt.ipp.isep.dei.repository.GamesRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.UserSession;

public class LoadGameController {

    private final GamesRepository repo = Repositories.getInstance().getGamesRepository();
    private final UserSession userSession = Repositories.getInstance().getAuthenticationRepository()
            .getCurrentUserSession();

    public CurrentGame loadGame(UserSession userSession, String fileName) throws Exception {
        return repo.loadGame(userSession, fileName);
    }

    public String[] listSavedGames(UserSession userSession) {
        if (!repo.isUserFolderEmpty(userSession)) {
            return repo.listSavedGames(userSession);
        }
        return null;
    }

    public UserSession getCurrentUser() {
        return userSession;
    }
}
