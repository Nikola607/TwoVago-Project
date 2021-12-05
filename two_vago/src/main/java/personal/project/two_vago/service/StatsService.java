package personal.project.two_vago.service;

import personal.project.two_vago.models.entities.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
