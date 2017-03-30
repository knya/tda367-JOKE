package utilities;

import com.badlogic.gdx.utils.Array;

public class PathFinder{
	private Array<Node> shortestPath;
	private Array<Node> allNodes;
	private float speed = 10;
	
	public PathFinder(Array<Node> allNodes){
			this.allNodes = allNodes;
	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	private void calculateShortest(){
		shortestPath = calcPixelPath(speed, allNodes); //just for testing. will be replaced with dijekstra
	}
	
	public Array<Node> getShortestPath(){
		
		if (shortestPath == null){
			calculateShortest();
			System.out.println(shortestPath.size);
		}
		System.out.println(shortestPath.size);
		return shortestPath;
	}
	
	public void setAllNodes(Array<Node> allNodes){
		this.allNodes = allNodes;
	}
	
	//calculates the specific coordinates between nodes with a certain jump
	private Array<Node> calcPixelPath(float speed, Array<Node> pathNodes){
		
		Array<Node> path = new Array<Node>();
		
		for(int i = 0; i < pathNodes.size -1; i++){
			Node goal = pathNodes.get(i+1);
			Node start = pathNodes.get(i);
			Node current = start;	//acts like a sprite. "Walks" along the node lines.
			float startX = start.getX();
			float startY = start.getY();
			float deltaX = goal.getX() - startX;	
			float deltaY = goal.getY() - startY;
			float startDistance = (float) Math.sqrt(deltaX*deltaX + deltaY*deltaY); //to conmpare with currentdistance to see if we have arrived at node yet
			Node direction = new Node(deltaX/startDistance, deltaY/startDistance); //normalized direction
			
			float deltaCurrentX;
			float deltaCurrentY;
			float currentDistance = 0; //start at zero because startDistance will never be <0
			
			while(currentDistance <= startDistance){
					Node nextStep = new Node(current.getX() + (direction.getX() * speed), current.getY() + (direction.getY() * speed));
					//nextstep: gives coordnitaes for the next step with a certain speed or jump. Moves from current position to current + direction*jump
					//slowly increments towards the goal node
					path.add(nextStep);
					current = nextStep; //update current
					deltaCurrentX = current.getX() - start.getX();
					deltaCurrentY = current.getY() - start.getY();
					currentDistance = (float) Math.sqrt(deltaCurrentX*deltaCurrentX + deltaCurrentY*deltaCurrentY); 
			}
			path.pop(); //remove last node to avoid going beond the goal node
			path.add(pathNodes.get(i+1)); //add goal node instead so we land on the correct node. 
			current = pathNodes.get(i+1);	//update current to match
		}
		for(Node node : path){
			System.out.println(node.getX() + "  " + node.getY());
		}
		return path;
		
		
	}
	

	
	
	
	
	
	
	
	
		
}