package com.javaciv.builder;

import java.util.HashMap;

import com.javaciv.type.InfrastructureType;
import com.javaciv.type.TechnologyID;
import com.javaciv.type.UniteType;
import com.javaciv.gameElement.Infrastructure;
import com.javaciv.gameElement.Military;
import com.javaciv.gameElement.Technology;
import com.javaciv.gameElement.Technology.TechnologyStates;
import com.javaciv.server.Server;

public class TechnologyTree {
    
    private Server server;

    public TechnologyTree(Server server) {
        this.server = server;
    }

    public HashMap<TechnologyID, Technology> map = new HashMap<TechnologyID, Technology>() {{
        put(TechnologyID.CATAPULTE, new Technology(TechnologyID.CATAPULTE, "Catapulte", 100, new TechnologyID[] {}, new TechnologyID[] {TechnologyID.CATAPULTE}, TechnologyStates.DISCOVERABLE) {
            @Override
            public void discover() {
                server.getDiscoveredUnit().militaryMap.put(UniteType.CATAPULTE, new Military(
                    "Catapulte",
                    null,
                    2,
                    140,
                    10,
                    100,
                    20
                ));
            }
        });

        put(TechnologyID.MURAILLE, new Technology(TechnologyID.MURAILLE, "Muraille", 100, new TechnologyID[] {TechnologyID.CATAPULTE}, new TechnologyID[] {}, TechnologyStates.UNKNOWN) {
            @Override
            public void discover() {
                server.getDiscoveredInfrastructures().map.put(InfrastructureType.MURAILLE, new Infrastructure(
                    200, 
                    2, 
                    0, 
                    0, 
                    0, 
                    0,
                    0));
            }
        });
    }};

    public void discover(TechnologyID techID) {
        Technology discoveredTech = map.get(techID);
        discoveredTech.discover();
        discoveredTech.setState(TechnologyStates.DISCOVERED);
        for (TechnologyID ID : map.get(techID).getNext()) {
            map.get(ID).prevDiscover(techID);
        }
    }

    public Technology getTechnology(TechnologyID techID) {
        Technology tech = map.get(techID);
        return new Technology(tech.getID(), 
                              tech.getName(), 
                              tech.getCost(), 
                              null, 
                              null, 
                              tech.getState()) {
                              public void discover() {}
        };
    }

}
