(ns sicpclojure.config)

(def build {:path-to-code "resources/code/"
            :path-to-text "resources/text/"
            :deploy-directory "deploy/"
            :ignore #{".scss"}
            :complete [10 11 12]})

(def templates {:css   ["style.css"
                        "solarized_light.css"]
                :fonts [["Lora" 
                          :size [400 700]]
                        ["Ubuntu Mono"]]
                :font-url "http://fonts.googleapis.com/css?family="
                :static-dir "static/"})
