// 1.3.3
// 어떤 클라이언트가 스택에 대해 push와 pop을 수 차례 번갈아 가며 섞어서 수행했다고 하자.
// push 작업은 숫자 0에서 9까지 순서대로 이루어진다. 
// pop의 결과를 순서대로 출력할 때 아래의 순열 a ~ h 중에서 발생할 수 없는 순열은 무엇인가?
public class Exercise01_03_03 {
	// sol)
	// a. 4 3 2 1 0 9 8 7 6 5 - 가능
	// 0 1 2 3 4 push
	// pop pop pop pop pop (4 3 2 1 0 출력)
	// 5 6 7 8 9 push
	// pop pop pop pop pop (9 8 7 6 5 출력)
	//
	// b. 4 6 8 7 5 3 2 9 0 1 - 불가능
	// 0 1 2 3 4 push
	// pop (4 출력)
	// 5 6 push - 현재 0 1 2 3 5 6
	// pop (6 출력)
	// 7 8 push  - 현재 0 1 2 3 5 7 8
	// pop (8 출력) - 현재 0 1 2 3 5 7
	// pop pop pop pop (7 5 3 2 출력)
	// 9 push - 현재 0 1 9
	// 9 0 1 불가능..
	// 
	// c. 2 5 6 7 4 8 9 3 1 0 - 가능
	// push - 0 1 2 pop - 2 현재 - 0 1
	// push - 3 4 5 pop - 5 현재 - 0 1 3 4
	// push - 6 	pop - 6
	// push - 7 	pop - 7
	//				pop - 4 현재 - 0 1 3
	// push - 8		pop - 8
	// push - 9		pop - 9
	// pop pop pop - 3 1 0
	//
	// d. 4 3 2 1 0 5 6 7 8 9 - 가능
	// push - 0 1 2 3 4 	pop - 4 3 2 1 0
	// push - 5 	pop - 5
	// push - 6 	pop - 6
	// push - 7 	pop - 7
	// push - 8 	pop - 8
	// push - 9 	pop - 9
	//
	// e. 1 2 3 4 5 6 9 8 7 0 - 가능
	// push - 0 1 	pop - 1 현재 - 0
	// push - 2		pop - 2
	// push - 3		pop - 3
	// push - 4		pop - 4
	// push - 5		pop - 5
	// push - 6		pop - 6
	// push - 7 8 9 pop - 9 8 7
	// 				pop - 0
	//
	// f. 0 4 6 5 3 8 1 7 2 9 - 불가능
	// push - 0		  pop - 0
	// push - 1 2 3 4 pop - 4 현재 - 1 2 3
	// push - 5 6 	  pop - 6 5 3 - 현재 1 2
	// push - 7 8	  pop - 8 현재 - 1 2 7
	// 불가능
	//
	// g. 1 4 7 9 8 6 5 3 0 2 - 가능
	// push - 0 1 	pop - 1
	// push - 2 3 4	pop - 4
	// push - 5 6 7 pop - 7
	// push - 8 9	pop - 9 8 6 5 3 2 0
	//
	// h. 2 1 4 3 6 5 8 7 9 0 - 가능
	// push - 0 1 2 	pop - 2 1
	// push - 3 4 		pop - 4 3
	// push - 5 6 		pop - 6 5
	// push - 7 8	 	pop - 8 7
	// push - 9			pop - 9 0
}
