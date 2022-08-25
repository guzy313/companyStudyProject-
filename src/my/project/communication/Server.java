package my.project.communication;

import my.project.communication.entity.ServerThread;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Server {
    private Map<String,ServerThread> threads  =  new HashMap<>();


    public void server(){




    }










    public Map<String,ServerThread> getThreads() {
        return threads;
    }

    public void setThreads(Map<String,ServerThread> threads) {
        this.threads = threads;
    }
}
