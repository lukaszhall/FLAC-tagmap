(ns flac-tagmap.core
  (:require [environ.core :refer [env]]
            [taoensso.timbre :as timbre
             :refer (log  trace  debug  info  warn  error  fatal  report
                     logf tracef debugf infof warnf errorf fatalf reportf
                     spy get-env log-env)])
  (:import (org.jaudiotagger.tag.flac FlacTag)
           (org.jaudiotagger.audio    AudioFileIO)
           (org.jaudiotagger.tag      FieldKey))
  (:gen-class))

;(timbre/set-level! :)

(def flac-loc (env :flac-read-dir))

(defn -main
  "Program entry."
  [& args]
  (println (str  "Reading from flac dir:" flac-loc ))
;  (debug (Exception. "Uh Oh!") "some arg" 2)
  (println (str "flac-dir:" (env :flac-src-dir))))


(def flac-src-dir (env :flac-src-dir))


(defn id3-vals
  "read all ID3 tags for a FLAC file"
  [file]
  (let [audio-file (AudioFileIO/read file)
        tag        (.getTag  audio-file)
        header     (.getAudioHeader audio-file)
        fields     {:album        FieldKey/ALBUM
                    :album-artist FieldKey/ALBUM_ARTIST
                    :artist       FieldKey/ARTIST
                    :bpm          FieldKey/BPM
                    :composer     FieldKey/COMPOSER
                    :disc         FieldKey/DISC_NO
                    :disc-total   FieldKey/DISC_TOTAL
                    :genre        FieldKey/GENRE
                    :title        FieldKey/TITLE
                    :track        FieldKey/TRACK
                    :track-total  FieldKey/TRACK_TOTAL
                    :year         FieldKey/YEAR}]
    (loop [field-seq (seq fields)
           field-map {}]
      (if (= 0 (count field-seq))
        field-map
        (let [next-field  (first field-seq)
              next-type   (first next-field)
              next-id3key (second next-field)]
          (recur (rest field-seq)
                 (assoc field-map
                        next-type
                        (.getFirst tag next-id3key))))))))



(defn find-flac-files
  "Return a sequence of all flac files in a given directory"
  [dir]
  (let [all-files  (clojure.java.io/file dir)
        flac-files (->> (file-seq all-files)
                        (filter #(clojure.string/ends-with? % ".flac")))]
    flac-files))

(defn song-composer-map
  "Build song composer map for a given list of flac files"
  [flac-files]
  (let [all-id3-tags (map id3-vals flac-files)]
    (loop [] true)))
