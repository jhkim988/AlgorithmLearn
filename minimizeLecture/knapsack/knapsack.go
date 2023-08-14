package main

import "fmt"

const LIM_LECTURE = 30

type Subject struct {
	Name    string `json:"name"`
	Lecture int    `json:"lecture"`
	Time    int    `json:"time"`
}

func Knapsack(args []Subject) []Subject {
	var sumLecture, sumTime int
	for _, sub := range args {
		sumLecture += sub.Lecture
		sumTime += sub.Time
	}

	dp := make([][]int, len(args)+1)
	for idx := range dp {
		dp[idx] = make([]int, sumLecture-LIM_LECTURE+1)
	}

	for i := 0; i < len(args); i++ {
		for j := sumLecture - LIM_LECTURE; j >= 0; j-- {
			if j >= args[i].Lecture {
				dp[i+1][j] = Max(dp[i][j], dp[i][j-args[i].Lecture]+args[i].Time)
			} else {
				dp[i+1][j] = dp[i][j]
			}
		}
	}
	fmt.Println("Minimal Time: ", sumTime-dp[len(args)][sumLecture-LIM_LECTURE])

	// trace
	trace := sumLecture - LIM_LECTURE
	optimal := make([]Subject, 0)
	for i := len(args); i > 0; i-- {
		if dp[i][trace] != dp[i-1][trace] {
			trace -= args[i-1].Lecture
		} else {
			optimal = append(optimal, args[i-1])
		}
	}

	return optimal
}

func Max(a, b int) int {
	if a >= b {
		return a
	}
	return b
}
