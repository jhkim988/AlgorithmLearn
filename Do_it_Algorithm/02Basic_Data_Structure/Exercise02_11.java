class Exercise02_11{
	static class YMD {
		static final int[][] mdays = { { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },
				{ 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };
		static int isLeap(int year) {
			return (year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0) ? 1 : 0;
		}
		static int[] carryMonth(int[] ymd) {
			while (ymd[1] < 1) {
				ymd[1] += 12;
				ymd[0]--;
			}
			while (ymd[1] > 12) {
				ymd[1] -= 12;
				ymd[0]++;
			}
			return ymd;
		}
		
		int y;
		int m;
		int d;
		
		YMD(int y, int m, int d) {
			int[] temp = {y, m, d};
			temp = carryMonth(temp);
			
			while (temp[2] < 1) {
				temp[2] += mdays[isLeap(temp[0])][temp[1] - 1];
				temp[1]--;
				temp = carryMonth(temp);
			}
			while (temp[2] > mdays[isLeap(temp[0])][temp[1] - 1]) {
				temp[2] -= mdays[isLeap(temp[0])][temp[1] - 1];
				temp[1]++;
				temp = carryMonth(temp);
			}
			this.y = temp[0];
			this.m = temp[1];
			this.d = temp[2];			
		}
		
		YMD after(int n) {
			return new YMD(this.y, this.m, this.d + n);
		}
		
		YMD before(int n) {
			return new YMD(this.y, this.m, this.d - n);
		}
	}
	
	public static void main(String[] args) {
		YMD date = new YMD(2021, 1, 500);
		System.out.printf("%d³â %d¿ù %dÀÏ", date.y, date.m, date.d);
	}
}