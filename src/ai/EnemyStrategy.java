package ai;


public class EnemyStrategy implements Enemies{

	private EnemyStrategy.Strategy strategy;
	
	public EnemyStrategy(int xPos, int yPos, int direction, EnemyStrategy.Strategy strategy){
		//super(xPos, yPos, direction);
		this.strategy = strategy;
	}
	
	public float speed(){
		return strategy.speed();
	}
	
	
	public void tick(){
		strategy.tick();
	}
	
	
	interface Strategy{
		public float speed();
		public void tick();
	}
	
}
