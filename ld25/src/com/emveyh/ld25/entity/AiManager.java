package com.emveyh.ld25.entity;

import com.badlogic.gdx.utils.Array;
import com.emveyh.ld25.MapCoord;
import com.emveyh.ld25.map.MapManager;
import com.emveyh.ld25.map.Tile;

public class AiManager {

	private static final AiManager INSTANCE = new AiManager();
	public static AiManager getInstance() {
		return AiManager.INSTANCE;
	}
	
	private AiManager() {}
	
	
	private Array<MapCoord> closed = new Array<MapCoord>();
	private Array<MapCoord> open = new Array<MapCoord>();
	private int maxDepth = 0;
	private int maxSearchDistance = 0;
	
	
	public Array<MapCoord> aStar(MapCoord start, MapCoord destination, Entity askingEntity) {
		
		Array<MapCoord> path = new Array<MapCoord>();
		
		//System.out.println("from: "+start.getX()+" "+start.getY()+" to: "+destination.getX()+" "+destination.getY());
		//if(MapManager.getInstance().getMap()[destination.getX()][destination.getY()].isAccessible() && !MapManager.getInstance().getMap()[destination.getX()][destination.getY()].occupiedByOtherEntity(askingEntity)) {
		if(!MapManager.getInstance().getMap()[destination.getX()][destination.getY()].isAccessible() || MapManager.getInstance().getMap()[destination.getX()][destination.getY()].occupiedByOtherEntity(askingEntity)) {
			
			//take alternative
			Array<Tile> alternatives = MapManager.getInstance().getMap()[destination.getX()][destination.getY()].getSurroundingTiles(1);
			int shortestIndex = 0;
			int shortestDistance = 1000;
			for(int i = 0; i < alternatives.size; i++) {
				if(alternatives.get(i).isAccessible() && !alternatives.get(i).occupiedByOtherEntity(askingEntity)) {
				int distance = calcDistance(start, alternatives.get(i).getMapCoord());
				if(i == 0) {
					shortestDistance = distance;
				} else if(distance < shortestDistance) {
					shortestIndex = i;
					shortestDistance = distance;
				}
				}
			}
			
			destination = alternatives.get(shortestIndex).getMapCoord(); 
		}

		closed.clear();
		open.clear();
		
		MapCoord current = null;
		
		//add all coords to open
		for(int x = 0; x < MapManager.getInstance().getMap().length; x++) {
			for(int y = 0; y < MapManager.getInstance().getMap()[x].length; y++) {
				if(x == start.getX() && y == start.getY()) {
					current = MapManager.getInstance().getMap()[x][y].getMapCoord();
				} else {
					open.add(MapManager.getInstance().getMap()[x][y].getMapCoord());
				}
				
			}
		}
		
		while(open.size != 0) {
			path.add(current);
			if(current.getX() == destination.getX() && current.getY() == destination.getY()) {
				break;
			}
			//remove from open
			open.removeValue(current, true);
			//add to close
			closed.add(current);
			
			MapCoord nextCoord = null;
			int nextCoordDistance = 0;
			
			//for(int x = -1; x < 2; x++) {
			//	for(int y = -1; y <2; y++) {
					
					for(int i = 0; i < 4; i++) {
					
					//if(x == 0 && y == 0) {
					//	continue;
					//}
					
					int xp = current.getX();
					int yp = current.getY();
					if(i == 0 && xp > 0) {
						xp--;
					} else if(i == 1 && xp < MapManager.getInstance().getMap().length-1 ) {
						xp++;
					} else if(i == 2 && yp > 0) {
						yp--;
					} else if(i == 3 && yp < MapManager.getInstance().getMap()[0].length-1) {
						
						yp++;
					}
					
					if(open.contains(MapManager.getInstance().getMap()[xp][yp].getMapCoord(), true)) {
					//Pick the shortest available path
					if(MapManager.getInstance().getMap()[xp][yp].isAccessible() && !MapManager.getInstance().getMap()[xp][yp].occupiedByOtherEntity(askingEntity)) {
						
						
						if(nextCoord == null) {
							nextCoord = MapManager.getInstance().getMap()[xp][yp].getMapCoord();
							nextCoordDistance = calcDistance(nextCoord, destination);
						} else {
							int distance = calcDistance(MapManager.getInstance().getMap()[xp][yp].getMapCoord(), destination);
							if(distance < nextCoordDistance) {
								nextCoord = MapManager.getInstance().getMap()[xp][yp].getMapCoord();
								nextCoordDistance = distance;
							}
						}
						
					}
					}
					
				}
		//	}
			
			if(nextCoord != null) {
			//System.out.println("path: "+nextCoord.getX()+" / "+nextCoord.getY());
			current = nextCoord;
			} else {
				break;
			}
			
		}
		
		System.out.println(destination.getX()+" "+destination.getY());
		
		return path;
		
	}
	
	private int calcDistance(MapCoord start, MapCoord end) {
		
		int result = 0;
		
		int x = start.getX();
		int y = start.getY();
		
		while(x != end.getX() || y != end.getY()) {
			
			if(x < end.getX()) {
				x++;
				result++;
			} else if(x > end.getX()) {
				x--;
				result++;
			} else if(y < end.getY()) {
				y++;
				result++;
			} else if(y > end.getY()) {
				y--;
				result++;
			}
		
			if(!open.contains(MapManager.getInstance().getMap()[x][y].getMapCoord(), true)) {
				result += 3;
			}
		}
		
		
				
		
		
		return result;
	}
	
}
