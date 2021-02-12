import java.util.Arrays;
import java.util.Comparator;


class Exercise06_16 {
	// Q16 Ű�� ���������� �ƴ϶� �÷��� ������������ �����ϴ� ���α׷��� �ۼ��ϼ���
	static class PhyscData {
		String name;
		int height;
		double vision;

		PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}

		public String toString() {
			return name + " " + height + " " + vision;
		}
		
		static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
		
		private static class HeightOrderComparator implements Comparator<PhyscData> {
			public int compare(PhyscData d1, PhyscData d2) {
				return (d1.height > d2.height) ? 1 :
					(d1.height < d2.height) ? -1 : 0;
			}
		}
		
		static final Comparator<PhyscData> VISION_ORDER = new VisionOrderComparator();
		private static class VisionOrderComparator implements Comparator<PhyscData> {
			public int compare(PhyscData d1, PhyscData d2) {
				return (d1.vision > d2.vision) ? -1 :
					(d1.vision < d2.vision) ? 1 : 0;
			}
		}
	}

	public static void main(String[] args) {
		PhyscData[] x = {
				new PhyscData("������", 162, 0.3),
				new PhyscData("������", 173, 0.7),
				new PhyscData("������", 175, 2.0),
				new PhyscData("ȫ����", 171, 1.5),
				new PhyscData("�̼���", 168, 0.4),
				new PhyscData("�迵��", 174, 1.2),
				new PhyscData("�ڿ��", 169, 0.8),
		};
		
		Arrays.sort(x, PhyscData.VISION_ORDER);
		
		System.out.println("��ü�˻� ����Ʈ");
		System.out.println("�̸�       Ű   �÷�");
		System.out.println("-----------------");
		for(int i = 0; i < x.length; ++i)
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
	}
}