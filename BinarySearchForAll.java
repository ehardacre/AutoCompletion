package autocomplete;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class BinarySearchForAll {
	// flag indicating whether a key occurs at all in the list
	public static final int NOT_FOUND = -1;

	/**
	 * Returns the index of the first element in aList that equals key
	 *
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Index of first item in aList matching key or -1 if not in aList
	 **/
	public static <Key> int firstIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
			//base case = the size of the list is one element
			if(aList.size() > 1){
				//split list in half 
				int half = (aList.size()/2);
				Key halfKey = aList.get(half);
				//perform binary search
				int result = comparator.compare(key, halfKey);
				if(result > 0){
					int secondHalf = half + firstIndexOf(aList.subList(half, aList.size()), key, comparator);
					if(secondHalf - half == NOT_FOUND){
						return NOT_FOUND;
					}
					return secondHalf;
				}else if(result < 0){
					int firstHalf = firstIndexOf(aList.subList(0, half), key, comparator);
					return firstHalf;
				}else{
					//if the element we checked matches our prefix we must make sure it is the first
					//before returning
					int firstHalf = firstIndexOf(aList.subList(0, half), key, comparator);
					if(firstHalf == NOT_FOUND){
						return half;
					}else{
						return firstHalf;
					}
				}
			}else{
				//that element either matches the queue or not
				int half = 0;
				Key halfKey = aList.get(half);
				int result = comparator.compare(key, halfKey);
				if(result == 0) { return half; }else{ return NOT_FOUND; }
			}
	}



	/**
	 * Returns the index of the last element in aList that equals key
	 * 
	 * @param aList
	 *            Ordered (via comparator) list of items to be searched
	 * @param key
	 *            item searching for
	 * @param comparator
	 *            Object with compare method corresponding to order on aList
	 * @return Location of last item of aList matching key or -1 if no such key.
	 **/
	public static <Key> int lastIndexOf(List<Key> aList, Key key,
			Comparator<Key> comparator) {
		//base case = the size of the list is one element
		if(aList.size() > 1){
			int half = (aList.size()/2);
			Key halfKey = aList.get(half);
			//perform binary search
			int result = comparator.compare(key, halfKey);
			if(result > 0){
				int secondHalf = half + lastIndexOf(aList.subList(half, aList.size()), key, comparator);
				if(secondHalf - half == NOT_FOUND){
					return NOT_FOUND;
				}
				return secondHalf;
			}else if(result < 0){
				int firstHalf = lastIndexOf(aList.subList(0, half), key, comparator);
				return firstHalf;
			}else{
				//if the element we checked matches our prefix we must make sure it is the last
				//before returning
				int secondHalf = lastIndexOf(aList.subList(half, aList.size()), key, comparator);
				if(secondHalf == NOT_FOUND){
					return half;
				}else{
					return half + secondHalf;
				}
			}
		}else{
			//that element either matches the queue or not
			int half = 0;
			Key halfKey = aList.get(half);
			int result = comparator.compare(key, halfKey);
			if(result == 0) { return half; }else{ return NOT_FOUND; }
		}
	}

}
