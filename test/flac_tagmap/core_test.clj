(ns flac-tagmap.core-test
  (:require [clojure.test     :refer :all]
            [flac-tagmap.core :refer :all]
            [midje.sweet      :refer :all]))


(def id3-maps
  [{:genre        "Jazz",
    :disc         "4",
    :artist       "Jack Teagarden",
    :title        "A Portrait of Mr. T",
    :year         "2003",
    :composer     "Don Goldie",
    :album        "The Complete Roulette Jack Teagarden Sessions (Disc 4)",
    :disc-total   "",
    :track-total  "",
    :bpm          "",
    :album-artist "Jack Teagarden",
    :track        "2"}
   {:genre        "Jazz",
    :disc         "4",
    :artist       "Jack Teagarden",
    :title        "Rockin' Chair",
    :year         "2003",
    :composer     "Hoagy Carmichael",
    :album        "The Complete Roulette Jack Teagarden Sessions (Disc 4)",
    :disc-total   "",
    :track-total  "",
    :bpm          "",
    :album-artist "Jack Teagarden",
    :track        "3"}
   {:genre        "Jazz",
    :disc         "4",
    :artist       "Jack Teagarden",
    :title        "In the Dark",
    :year         "2003",
    :composer     "Bix Beiderbecke",
    :album        "The Complete Roulette Jack Teagarden Sessions (Disc 4)",
    :disc-total   "",
    :track-total  "",
    :bpm          "",
    :album-artist "Jack Teagarden",
    :track        "4"}])


(fact "About convert-id3-to-key-value-map"
      (convert-id3-to-key-value-map
       (first id3-maps) :song :composer) => {}
      (convert-id3-to-key-value-map
       (first id3-maps) :title :composer) => {"A Portrait of Mr. T", "Don Goldie" })


(fact "About single-key-reductor"
      (single-key-reductor {:a 1 :b 2} {:c 3})
      => {:a 1 :b 2 :c 3}
      (single-key-reductor {:a 1 :b 2} {:a 1}) => {:a 1 :b 2}
      (single-key-reductor {:a 1 :b 2} {:a 2}) => {:a 2 :b 2})
