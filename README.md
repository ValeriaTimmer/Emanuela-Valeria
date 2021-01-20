# Emanuela-Valeria
Esame UNIVPM Programmazione ad Oggetti gennaio 2021

# Autori 
- Emanuela Saleggia 50%
- Valeria Timmer 50%

# SpringBoot REST API OpenWeather OOP
 L'applicazione utilizza l'API **Call 5 day / 3 hour forecast data** di OpenWeather
 e ne manipola i dati con lo scopo di ottenere informazioni riguardo alle previsioni
 e all'umidità di una determinata città. 
 In partiolare l'applicazione sarà in grado di: 
 - Restituire dei metadati in formato JSON 
 - Restituire delle statistiche su alcuni dati in formato JSON 
 - Restituire statistiche riguardo il numero di previsioni azzeccate in formato JSON
 
## Statistiche
Le statistiche riguardano: 
- Valore minimo
- Valore massimo
- Media
- Varianza

Le statistiche vengono effettuate su:
 - Umidità 
 - Temperatura

Tali statistiche saranno soggette inoltre ad un filtraggio in base alla periodicità desiderata.

Vengono inoltre effettute delle statistiche riguardo: 
 - Numero di previsioni azzeccate dell'umidità con una soglia di errore del 10% per ogni città

# UML

## UseCaseDiagram
![Casi d'uso](https://user-images.githubusercontent.com/75066505/105203213-24025080-5b43-11eb-9d64-5973a1c0cc40.png)

## ClassDiagram

### Package Main
![Schermata 2020-12-23 alle 17 52 39](https://user-images.githubusercontent.com/75066505/103020235-57ec5500-4548-11eb-8c4c-8a45fec021eb.png)

## Package Controller
Package contente la classe che gestisce le chiamate al server

![controller](https://user-images.githubusercontent.com/75066505/105203329-472d0000-5b43-11eb-8f14-df46672cbc79.png)

### Package Exception
Package contente le eccezioni personalizzate

![exception](https://user-images.githubusercontent.com/75066505/105203453-688dec00-5b43-11eb-8e10-da99438949e9.png)

### Package Filter
Package contente le classi per la gestione dei filtri 

![filter](https://user-images.githubusercontent.com/75066505/105203543-88bdab00-5b43-11eb-9095-ded5409542a9.png)

### Package Model
Package contente la classe che modella i dati 

![model](https://user-images.githubusercontent.com/75066505/105203991-0386c600-5b44-11eb-824b-aa2b39972440.png)

### Package Service
Package contente le classi necessarie per l'elaborazione dei metodi richiamati dal Controller

![service](https://user-images.githubusercontent.com/75066505/105204106-23b68500-5b44-11eb-8a06-3ffb2856c066.png)

### Package Statistics
Package contenente le classi per la generazione delle statistiche

![statistics](https://user-images.githubusercontent.com/75066505/105204229-3d57cc80-5b44-11eb-89eb-136411ddc484.png)

### Package Utils
Package contenete le classi di supporto per l'elaborazione e la manipolazione dei dati 

![utils](https://user-images.githubusercontent.com/75066505/105204346-595b6e00-5b44-11eb-926a-4244b5b3fc3a.png)

### Test effettuati sull'applicazione
Sono stati effettuati test sul statistiche e eccezioni 
In particolare la classe: 
- *StatsTest* esegue due test: il primo riguardante il calcolo delle statistiche, il secondo riguardante
l'eccezione personalizzata StatsException
- *FilterTest* esegue quattro test: i primi tre riguardanti l'eccezione personalizzata FilterException
(utilizzata in tre differenti classi), il quarto tiguardante l'eccezione personalizzata DataFormatException

![test](https://user-images.githubusercontent.com/75066505/105204471-7ee87780-5b44-11eb-8a17-74ad7c540883.png)

## SequenceDiagram

### Prima parte
![diagramma_delle_sequenze](https://user-images.githubusercontent.com/75066510/105205242-50b76780-5b45-11eb-9cb3-119c2bd1ff3f.png)

### Seconda parte 
![Diagramma_statistiche](https://user-images.githubusercontent.com/75066510/105205377-7fcdd900-5b45-11eb-83b7-8b3a9dc9d52d.png)



# Rotte Applicazione
Attraverso delle API REST (GET o POST) si possono eseguire delle richieste che 
differiscono in base alle diverse rotte mostrate nella tabella sottostante

TIPO | ROTTA | DESCRIZIONE
-----|-------|------------
GET  |/metadata | Restituisce i metadata 
POST |/stats  | Effettua le statistiche in base a parametri scelti dall'utente 
GET  |/data | Restituisce le previsioni meteo per i successivi 5 giorni di una città
POST |/forecast | Effettua le statistiche riguardo le previsioni meteoreologiche in base a paramtri scelti dall'utente

### Parametri richiesti:

#### Rotta GET/metadata
Restituisce i seguenti metadata nel seguente formato: 



#### Rotta POST/stats
Per visualizzare le statistiche desiderate viene richiesto all'utente di inserire alcuni parametri all'interno di 
un JSONObject di questo formato: 
- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*
- "type" : parametro sul quale vengono effettuate le statistiche. Può assumere i valori *"humidity" o "temperature"*
- "from" : data di inizio 
- "to": data di fine

Restituisce i dati nel seguente formato:



#### Rotta GET/data
Per visualizzare le previsioni per i successivi 5 giorni riguardo umidità e temperatura viene richiesto all'utente di 
inserire alcuni parametri:
- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*

Restituisce i dati nel seguente formato: 



#### Rotta POST/forecast
Per visualizzare le statistiche riguardo i valori di umidità azzeccati viene richiesto all'utente di inserire alcuni parametri:
- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*
- "date" : giorno del quale si vogliono ottenere i risultati 

Restituisce i dati nel seguente formato: 



# Possibili Eccezioni 
Dall'applicazione vengono gestite alcune eccezioni in maniera personalizzata:
- DataFormatException
- FilterException 
- StatsException 
- UrlException 

In particolare: 
- *DataFormatException :* segnala che si è verificato un errore di formato dati.
Questa eccezione estende da java.text.ParseException la quale segnala che è stato raggiunto un errore inaspettatamente 
durante l'analisi dei dati.
Può essere lanciata quando le date inserite dall'utente nelle chiamate non sono corrette, in particolare 
la data di fine precede la data di inizio.
- *FilterException :* segnala che si è verificato un errore nel filtraggio dei dati.
Questa eccezione estende da java.io.IOException la quale segnala operazioni di I / O non riuscite. 
Può essere lanciata quando i parametri di filtraggio inseriti dall'utente sono nulli.
- *StatsException :* segnala che si è verificato un errore nel calcolo delle statistiche.
Questa eccezione estende da java.lang.Exception.
Può essere lanciata quando non sono disponibili i valori per effettuare le statistiche.
- *UrlException :* segnala che si è verificato un errore nella connessione al sito.
Questa eccezione estende da java.net.MalformedURLException la quale segnala che si è verificato un URL non valido.
Può essere lanciata quando l'Url del sito risulta errato.
