// 1.3.3
// � Ŭ���̾�Ʈ�� ���ÿ� ���� push�� pop�� �� ���� ������ ���� ��� �����ߴٰ� ����.
// push �۾��� ���� 0���� 9���� ������� �̷������. 
// pop�� ����� ������� ����� �� �Ʒ��� ���� a ~ h �߿��� �߻��� �� ���� ������ �����ΰ�?
public class Exercise01_03_03 {
	// sol)
	// a. 4 3 2 1 0 9 8 7 6 5 - ����
	// 0 1 2 3 4 push
	// pop pop pop pop pop (4 3 2 1 0 ���)
	// 5 6 7 8 9 push
	// pop pop pop pop pop (9 8 7 6 5 ���)
	//
	// b. 4 6 8 7 5 3 2 9 0 1 - �Ұ���
	// 0 1 2 3 4 push
	// pop (4 ���)
	// 5 6 push - ���� 0 1 2 3 5 6
	// pop (6 ���)
	// 7 8 push  - ���� 0 1 2 3 5 7 8
	// pop (8 ���) - ���� 0 1 2 3 5 7
	// pop pop pop pop (7 5 3 2 ���)
	// 9 push - ���� 0 1 9
	// 9 0 1 �Ұ���..
	// 
	// c. 2 5 6 7 4 8 9 3 1 0 - ����
	// push - 0 1 2 pop - 2 ���� - 0 1
	// push - 3 4 5 pop - 5 ���� - 0 1 3 4
	// push - 6 	pop - 6
	// push - 7 	pop - 7
	//				pop - 4 ���� - 0 1 3
	// push - 8		pop - 8
	// push - 9		pop - 9
	// pop pop pop - 3 1 0
	//
	// d. 4 3 2 1 0 5 6 7 8 9 - ����
	// push - 0 1 2 3 4 	pop - 4 3 2 1 0
	// push - 5 	pop - 5
	// push - 6 	pop - 6
	// push - 7 	pop - 7
	// push - 8 	pop - 8
	// push - 9 	pop - 9
	//
	// e. 1 2 3 4 5 6 9 8 7 0 - ����
	// push - 0 1 	pop - 1 ���� - 0
	// push - 2		pop - 2
	// push - 3		pop - 3
	// push - 4		pop - 4
	// push - 5		pop - 5
	// push - 6		pop - 6
	// push - 7 8 9 pop - 9 8 7
	// 				pop - 0
	//
	// f. 0 4 6 5 3 8 1 7 2 9 - �Ұ���
	// push - 0		  pop - 0
	// push - 1 2 3 4 pop - 4 ���� - 1 2 3
	// push - 5 6 	  pop - 6 5 3 - ���� 1 2
	// push - 7 8	  pop - 8 ���� - 1 2 7
	// �Ұ���
	//
	// g. 1 4 7 9 8 6 5 3 0 2 - ����
	// push - 0 1 	pop - 1
	// push - 2 3 4	pop - 4
	// push - 5 6 7 pop - 7
	// push - 8 9	pop - 9 8 6 5 3 2 0
	//
	// h. 2 1 4 3 6 5 8 7 9 0 - ����
	// push - 0 1 2 	pop - 2 1
	// push - 3 4 		pop - 4 3
	// push - 5 6 		pop - 6 5
	// push - 7 8	 	pop - 8 7
	// push - 9			pop - 9 0
}
