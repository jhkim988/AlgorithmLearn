package main

import (
	"encoding/json"
	"fmt"
	"io"
	"os"
)

type SubjectData struct {
	Data []Subject `json:"data"`
}

func main() {
	jsonFile, _ := os.Open("subject.json")
	defer jsonFile.Close()

	byteValue, _ := io.ReadAll(jsonFile)
	var subjectData SubjectData

	json.Unmarshal(byteValue, &subjectData)

	result := Knapsack(subjectData.Data)
	for _, sub := range result {
		fmt.Printf("%v\n", sub)
	}
}
