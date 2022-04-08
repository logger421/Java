import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class Shop implements ShopInterface {
	
	Map<String, Integer> currentStock = new HashMap<>();
	Map<String, Object> objectsLocks = new HashMap<>();
	
	@Override
	public void delivery(Map<String, Integer> goods) {
		Iterator<Map.Entry<String, Integer>> iterator = goods.entrySet().iterator();
		
		while(iterator.hasNext()) {			
			Map.Entry<String, Integer> article = iterator.next();
			synchronized (this) {
				if (currentStock.containsKey(article.getKey())) {
					currentStock.put(article.getKey(), ( article.getValue() + currentStock.get(article.getKey()) ) );
				}
				else {
					currentStock.put(article.getKey(), article.getValue());
					if (objectsLocks.get(article.getKey()) == null) {
						objectsLocks.put(article.getKey(), new Object());
					}
				}
			}
			synchronized(objectsLocks.get(article.getKey())) {
				objectsLocks.get(article.getKey()).notifyAll();
			}
		}
	}

	@Override
	public boolean purchase(String productName, int quantity) {
		if (!objectsLocks.containsKey(productName))
			objectsLocks.put(productName, new Object());
		
		synchronized (objectsLocks.get(productName)) {
			if (currentStock.containsKey(productName) && currentStock.get(productName) >= quantity) {
				int currentAmount = currentStock.get(productName);
				currentStock.put(productName, currentAmount - quantity);
				return true;
			}
			else {
				try {
					objectsLocks.get(productName).wait();
				} catch (InterruptedException ex) { ex.printStackTrace(); }
				
				if (currentStock.containsKey(productName) && currentStock.get(productName) >= quantity) {
					int currentAmount = currentStock.get(productName);
					currentStock.put(productName, currentAmount - quantity);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Map<String, Integer> stock() {
		// TODO Auto-generated method stub
		return currentStock;
	}

}
