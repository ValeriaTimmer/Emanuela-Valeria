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
 
 
 L'applicativo è in grado di salvare i dati di quattro città costantemente monitorate
 (*Rome, City of London, Berlin, Paris*) ogni ora utilizzando l'annotazione @Scheduled di
 SpringBoot che effettua la chiamata al sito di OpenWeather e crea uno storico con i dati
 salvati nel file *allValues.json*. Inoltre nel file *parsing.json* vengono salvati gli
 ultimi dati della chiamata al sito. 
 
## Statistiche
Le statistiche riguardano: 
- Valore minimo
- Valore massimo
- Media
- Varianza

Le statistiche vengono effettuate su:
 - Umidità 
 - Temperatura

Tali statistiche saranno soggette inoltre ad un filtraggio relativo ad un periodo desiderato, quindi possono 
essere effettutate anche su periodicità giornaliera.

Vengono inoltre effettute delle statistiche riguardo: 
  Numero di previsioni azzeccate dell'umidità con una soglia di errore del 20% per ogni città.

# Rotte Applicazione
Attraverso delle API REST (GET o POST) si possono eseguire delle richieste che 
differiscono in base alle diverse rotte mostrate nella tabella sottostante

TIPO | ROTTA | DESCRIZIONE
-----|-------|------------
GET  |/metadata | Restituisce i metadata 
GET  |/data | Restituisce le previsioni meteo  ogni 3 ore per i successivi 5 giorni di una città
POST |/stats  | Effettua le statistiche in base a parametri scelti dall'utente 
POST |/forecasts | Effettua le statistiche riguardo le previsioni meteoreologiche in base a paramtri scelti dall'utente
POST |/dailystats | Effettua le statistiche in base a parametri scelti dall'utente su periodicità giornaliera

### Parametri richiesti:

#### Rotta GET/metadata
Restituisce i seguenti metadata nel seguente formato: 
![screen_metadata](https://user-images.githubusercontent.com/75066510/105228126-769f3500-5b62-11eb-86da-097e1000c364.png)

#### Rotta GET/data
Per visualizzare le previsioni per i successivi 5 giorni riguardo umidità e temperatura viene richiesto all'utente di 
inserire alcuni parametri:

![screen_data](https://user-images.githubusercontent.com/75066510/105322383-1a233080-5bc9-11eb-8162-52562d8e45eb.png)

- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*

Restituisce i dati nel seguente formato: 

![screen_data1](https://user-images.githubusercontent.com/75066510/105322557-55bdfa80-5bc9-11eb-98f1-a5dd0e523e1e.png)

![screen_data2](https://user-images.githubusercontent.com/75066510/105322709-83a33f00-5bc9-11eb-8e4f-c9529d91fc4c.png)

#### Rotta POST/stats
Per visualizzare le statistiche desiderate viene richiesto all'utente di inserire alcuni parametri all'interno di 
un JSONObject di questo formato: 

![screen_body](https://user-images.githubusercontent.com/75066510/105228436-e57c8e00-5b62-11eb-8a70-91bd140c4a7a.png)

![screen_body2](https://user-images.githubusercontent.com/75066510/105322897-c36a2680-5bc9-11eb-8f94-e2dc661a8ff0.png)

- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*
- "type" : parametro sul quale vengono effettuate le statistiche. Può assumere i valori *"humidity" o "temperature"*
- "from" : data di inizio 
- "to": data di fine

Restituisce i dati nel seguente formato:

![screen_stats](https://user-images.githubusercontent.com/75066510/105228745-3a200900-5b63-11eb-87ea-a513253e3572.png)

![screen_stats2](https://user-images.githubusercontent.com/75066510/105323013-ea285d00-5bc9-11eb-836e-9a1394e714b5.png)

#### Rotta POST/forecasts
Per visualizzare le statistiche riguardo i valori di umidità azzeccati viene richiesto all'utente di inserire alcuni parametri:

![screen_parametriForecast](https://user-images.githubusercontent.com/75066510/105229845-d1399080-5b64-11eb-8845-8900d34ddd27.png)

- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*
- "date" : giorno del quale si vogliono ottenere i risultati 

Restituisce i dati nel seguente formato: 

![screen_forecast2](https://user-images.githubusercontent.com/75066510/105325443-e3e7b000-5bcc-11eb-91aa-8c9963942666.png)


#### Rotta POST/dailystats
Per visualizzare le statistiche su periodicità giornaliera viene richiesto all'utente di inserire alcuni parametri: 

![screen_dailystats_param](https://user-images.githubusercontent.com/75066510/105323195-252a9080-5bca-11eb-80ad-bb852f804644.png)


- "city" : nome della città. Può assumere i valori *"Rome", "City of London", "Berlin" o "Paris"*
- "type" : parametro sul quale vengono effettuate le statistiche. Può assumere i valori *"humidity" o "temperature"*
- "from" : data di inizio 
- "to": data di fine

Restituisce i dati nel seguente formato:

![screen_dailystats1](https://user-images.githubusercontent.com/75066510/105323475-776bb180-5bca-11eb-9802-ffeead309abe.png)

![screen_dailystats2](https://user-images.githubusercontent.com/75066510/105323617-9c602480-5bca-11eb-9d66-570eae53cf8e.png)

![screen_dailystats3](https://user-images.githubusercontent.com/75066510/105323774-d16c7700-5bca-11eb-9aaf-feea095aa6ed.png)

# UML

## UseCaseDiagram
![Casi d'uso](https://user-images.githubusercontent.com/75066505/105203213-24025080-5b43-11eb-9d64-5973a1c0cc40.png)

## ClassDiagram

### Package Main
![Schermata 2020-12-23 alle 17 52 39](https://user-images.githubusercontent.com/75066505/103020235-57ec5500-4548-11eb-8c4c-8a45fec021eb.png)

### Package Controller
Package contente la classe che gestisce le chiamate al server

![Schermata 2021-01-20 alle 23 05 25](https://user-images.githubusercontent.com/75066505/105247026-6a23d800-5b74-11eb-81cd-a3e67cf856a8.png)

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
(utilizzata in tre differenti classi), il quarto riguardante l'eccezione personalizzata DataFormatException

![test](https://user-images.githubusercontent.com/75066505/105204471-7ee87780-5b44-11eb-8a17-74ad7c540883.png)

## SequenceDiagram

### Prima parte
- Chiamata **/metadata** : il Controller effettua una chiamata al metodo getMetadata() della classe CityServiceImpl per la restituizione dei metadata.
- Chiamata **/data?city=cityName** : il Controller effettua una chiamata al metodo getData() della classe CityServiceImpl per la resituzione dei dati. Successivamente la classe CityServiceImpl chiama il metodo getAllData() della classe DataBase, la quale a sua volta chiama il metodo filtersCity() della classe CityFilter. Quest'ultima richiama poi il metodo getCityFiltered() della classe FiltersUtils che effettua il filtraggio dei dati, richiamando il metodo Parsing() della classe DownloadCity(), in base al nome della città inserito.
Dunque il controller resituisce i valori di umidità e temperatura previsti per i successivi 5 giorni per la città desiderata *(cityName)*.
![diagramma_delle_sequenze](https://user-images.githubusercontent.com/75066510/105205242-50b76780-5b45-11eb-9cb3-119c2bd1ff3f.png)

### Seconda parte 
- Chiamata **/stats** : il Controller effettua una chiamata al metodo getStats() della classe CityServiceImpl per la restituzione dei dati desiderati. Quest'ultima richiama il metodo Statistics() della classe Stats la quale si occupa di ritornare i valori delle statistiche. A sua volta questa classe richiama il metodo getStats() della classe StatsUtils che si occupa della manipolazione dei dati richiamando i metodi presenti nella classe StatisticsCalculator (calcolatore di statistiche). Le statistiche vengono effettuate sui dati presenti nel JSONArray ritornato dal metodo getAllData() della classe DataBase, il quale richiama in modo diretto i metodi per il filtraggio delle città (filtersCity() della classe CityFilter e getCityFiltered() della classe FiltersUtils i quali a loro volta richiamano il metodo Parsing() della classe DownloadCity() che ha un legame diretto con il sito di OpenWeather).
Dunque il controller restituisce i valori delle statistiche di umidità o temperatura di una determinata città relativamente ad un particolare periodo desiderato. 
- Chiamata **/forecasts?date=data&city=cityName** : il Controller effettua una chiamata al metodo getForecasts() della classe CityServiceImpl per la restituzione dei dati desiderati. Quest'ultima richiama il metodo confrontaValori() della classe Forecasts il quale ritornerà un JSONObject contente il numero di valori di umidità azzeccati. Il metodo confrontaValori() richiama a sua volta due metodi arrayPrevisioni1() ed arrayPrevisioni2(): il primo richiama il metodo Parsing() della classe DownloadCity() andando a prelevare i dati desiderati salvati in precedenza; il secondo richiama il metodo previsioniAttuali() che a sua volta richiama il metodo toOpenWeather() della classe DownloadCity() il quale effettua una chiamata direttamente al sito di OpenWeather. Il confronto dunque avviene tra i valori presenti nel file *parsing.json* che costituisce lo storico e i valori restituiti dal sito. Dunque il controller restituisce il numero dei valori dell'umidità azzeccati di una determinata città *(cityName)* relativamente alla data inserita dall'utente *(data)*.
- Chiamata **/dailystats/?city=cityName&type=parameter&from=date1&to=date2** : il Controller effettua una chiamata al metodo getDailyStats() della classe CityServiceImpl per la restituzione dei dati desiderati. Quest'ultima richiama il metodo filtersCity() della classe TypeFilter() che a sua volta richiama il metodo getTypeFiltered() della classe FilterUtils. Quest'ultimo richiamando il metodo getAllData() della classe DataBase e il metodo Statistics della classe StatUtils ritorna un JSONArray contente le statistiche di una determinata città con periodicità giornaliera. Dunque il controller restituisce i valori delle statistiche di umidità o temperatura *(parameter)* di una determinata città *(cityName)* relativamente ad un periodo desiderato *(from, to)* in base ad una periodicità giornalierà.

![Diagramma_statistiche](https://user-images.githubusercontent.com/75066510/105324020-23150180-5bcb-11eb-9eff-b7dd3d23603c.png)

## Possibili eccezioni
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
