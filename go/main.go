package main

import (
	"fmt"
	"github.com/peshrus/top-js-libraries/go/topjslibs"
	"io"
	"log"
	"net/http"
	"os"
)

func main() {
	argsWithoutProg := os.Args[1:]

	log.SetFlags(log.Lmicroseconds)

	if len(argsWithoutProg) == 0 {
		_, err := os.Stderr.WriteString("Specify the Google search query, please")
		panic(err)
	}

	searchStr := argsWithoutProg[0]
	log.Println("Query: " + searchStr)

	log.Println("Start")

	topJsLibraries := topjslibs.TopJsLibraries{LinksLimit: 5, TopLimit: 5, FetchHtml: fetchHtml}
	count := topJsLibraries.Count(searchStr)

	log.Println("Finish")

	for _, value := range count {
		fmt.Printf("[%d] %s\n", value.Num, value.Js)
	}
}

func fetchHtml(url string) string {
	client := &http.Client{}

	req, err := http.NewRequest("GET", url, nil)
	if err != nil {
		log.Println(err)
		return ""
	}

	req.Header.Add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:84.0) Gecko/20100101 Firefox/84.0")
	req.Header.Add("Referer", "http://www.google.com")

	resp, err := client.Do(req)
	if err != nil {
		log.Println(err)
		return ""
	}

	if resp.StatusCode < 200 || resp.StatusCode >= 400 {
		log.Printf("HTTP Status %d: %s\n", resp.StatusCode, url)
		return ""
	}

	defer func() {
		closeErr := resp.Body.Close()
		if closeErr != nil {
			log.Println(closeErr)
		}
	}()

	body, err := io.ReadAll(resp.Body)
	if err != nil {
		log.Println(err)
		return ""
	}

	return string(body)
}
