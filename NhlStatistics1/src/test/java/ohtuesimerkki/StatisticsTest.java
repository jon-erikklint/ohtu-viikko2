/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author klint
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Staatistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void findsName(){
        Player player = stats.search("Semenko");
        
        assertEquals(player.getName(), "Semenko");
        
        player = stats.search("sdsdsd");
        
        assertEquals(player, null);
    }
    
    @Test
    public void findsTeam(){
        List<Player> team = stats.team("EDM");
        Collections.sort(team);
        
        assertEquals(team.size(), 3);
        assertEquals(team.get(0).getName(), "Gretzky");
        
        team = stats.team("ASD");
        
        assertEquals(team.size(), 0);
    }
    
    @Test
    public void findsBests(){
        List<Player> best = stats.topScorers(1);
        
        assertEquals(best.get(0).getName(), "Gretzky");
        assertEquals(best.size(), 1);
        
        best = stats.topScorers(5);
        
        assertEquals(best.size(), 5);
        assertEquals(best.get(4).getName(), "Semenko");
    }
}
