package optix.commons;

import optix.commons.model.ShowHistoryMap;
import optix.commons.model.ShowMap;
import optix.commons.model.Theatre;

public class Model {
    private ShowHistoryMap showsHistory = new ShowHistoryMap();
    private ShowMap shows = new ShowMap();
    private ShowMap showsGUI = new ShowMap();

    private Theatre show;

    public Model(Storage storage) {
        storage.loadShows(shows, showsGUI, showsHistory);
        storage.loadArchive(showsHistory);
        storage.writeArchive(showsHistory);
    }

    public ShowMap getShows() {
        return shows;
    }

    public ShowHistoryMap getShowsHistory() {
        return showsHistory;
    }

    public void setShows(ShowMap shows) {
        this.shows = shows;
    }

    public void setShowsHistory(ShowHistoryMap showsHistory) {
        this.showsHistory = showsHistory;
    }

    public void setShow(Theatre show) {
        this.show = show;
    }

    public Theatre getShow() {
        return show;
    }

    public void setShowsGUI(ShowMap showsGUI) {
        this.showsGUI = showsGUI;
    }

    public ShowMap getShowsGUI() {
        return showsGUI;
    }
}
