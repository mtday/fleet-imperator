package game.server.rest.resource.v1.game;

import game.common.model.Game;
import game.server.db.GameStore;

import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/v1/games/{id}")
public class DeleteGame {
    private final GameStore gameStore;

    @Inject
    public DeleteGame(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    @DELETE
    @Produces(APPLICATION_JSON)
    public Game deleteGame(@PathParam("id") String id) {
        Game game = gameStore.get(id)
                .orElseThrow(() -> new NotFoundException("Game with id " + id + " not found"));
        gameStore.delete(id);
        return game;
    }
}
