package myDataStruct;

public class ShortX {

	public void insertSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int temp = array[i];
			int j;
			for (j = i; j > 0 && temp < array[j - 1]; j--) {
				array[j] = array[j - 1];
			}
			array[j] = temp;
			for (int k = 0; k < array.length; k++) {
				System.out.print(array[k] + " ");
			}
			System.out.println();
		}

	}

	public void bubbleShort(int array[]) {
		int temp = 0;
		for (int i = array.length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (array[j] > array[j + 1]) {

					// swap
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;

					// display
					for (int k = 0; k < array.length; k++) {
						System.out.print(array[k] + " ");
					}
					System.out.println();
				}
			}
		}
	}

	public void selectShot(int array[]) {
		int temp;
		int min;
		for (int i = 0; i < array.length; i++) {
			min=i;
			for (int j = i+1; j < array.length; j++) {
				if(array[j]<array[min]){
					min=j;
				}
			}
			
			temp=array[min];
			array[min]=array[i];
			array[i]=temp;
			
			// display
			for (int k = 0; k < array.length; k++) {
				System.out.print(array[k] + " ");
			}
			System.out.println();
		}
	}
}
