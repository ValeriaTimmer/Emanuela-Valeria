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

Le statistiche saranno effettuate su:
 - Umidità 
 - Temperatura

Le statistiche riguardanti le previsioni azzeccate sono effettuate in base ad una soglia
di errore ed in base ai giorni di predizione (da 1 a 5).

Tali statistiche saranno soggette inoltre ad un filtraggio in base alla periodicità desiderata.


# UML

## UseCaseDiagram
![Casi d'uso](https://user-images.githubusercontent.com/75066505/105203213-24025080-5b43-11eb-9d64-5973a1c0cc40.png)

## ClassDiagram

### Package Main
![Schermata 2020-12-23 alle 17 52 39](https://user-images.githubusercontent.com/75066505/103020235-57ec5500-4548-11eb-8c4c-8a45fec021eb.png)

## Package Controller
![controller](https://user-images.githubusercontent.com/75066505/105203329-472d0000-5b43-11eb-8f14-df46672cbc79.png)

### Package Exception
![exception](https://user-images.githubusercontent.com/75066505/105203453-688dec00-5b43-11eb-8e10-da99438949e9.png)

### Package Filter
![filter](https://user-images.githubusercontent.com/75066505/105203543-88bdab00-5b43-11eb-9095-ded5409542a9.png)

### Package Model
![model](https://user-images.githubusercontent.com/75066505/105203991-0386c600-5b44-11eb-824b-aa2b39972440.png)

### Package Service
![service](https://user-images.githubusercontent.com/75066505/105204106-23b68500-5b44-11eb-8a06-3ffb2856c066.png)

### Package Statistics
![Schermata 2020-12-31 alle 12 02 08](https://user-images.githubusercontent.com/75066505/103407865-6ba74500-4b60-11eb-80f7-e18568592a45.png)

### Package Utils
![utils](https://user-images.githubusercontent.com/75066510/103557783-003cda80-4eb4-11eb-9dab-bdfd2e47b7a8.png)



# Rotte Applicazione
Attraverso delle API REST (GET o POST) si possono eseguire delle richieste che 
differiscono in base alle diverse rotte mostrate nella tabella sottostante

TIPO | ROTTA | DESCRIZIONE
-----|-------|------------
GET  |/metadata | Restituisce i metadata 
POST |/stats  | Effettua le statistiche in base a parametri scelti dall'utente 
POST |/forecast | Effettua le statistiche riguardo le previsioni meteoreologiche in base a paramtri scelti dall'utente

### Parametri richiesti:
Per visualizzare le statistiche desiderate viene richiesto all'utente di inserire alcuni parametri: 
- "city" : nome della città 
- "country" : sigla dello stato della città (es. "IT" per "ITALY")
- "type" : parametro sul quale vengono effettuate le statistiche. Può assumere i valori "humidity" o "temperature"
- "from" : data di inizio
- "to": data di fine

Per visualizzare le statistiche riguardo le previsioni metereologiche viene richiesto all'utente di inserire alcuni paramtri:
- "city" : nome della città
- "country" : sigla dello stato della città (es. "IT" per "ITALY")
- "period" : numero di giorni di predizione. Può assumere valori da 1 a 5
