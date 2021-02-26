package topjslibs

import "io/ioutil"

func getHtmlFrom(fileName string) string {
	dat, _ := ioutil.ReadFile("../../html/" + fileName)
	return string(dat)
}
