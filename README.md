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
 - Restituire dei dati filtrati in formato JSON
 - Restituire delle statistiche su alcuni dati in formato JSON 
 
## Filtri 
Il filtraggio dei dati avviene in base:
 - Nome della città e stato 
 - Intervalli di umidità (in percentuale)
 - Intervalli di temperatura (misurati in Kelvin) 
 - Descrizione metereologica e nome della città

## Statistiche
Le statistiche riguardano: 
- Valore minimo
- Valore massimo
- Media
- Varianza

Le statistiche saranno effettuate su:
 - Umidità
 - Temperatura


# UML

## UseCaseDiagram
![Schermata 2020-12-16 alle 16 36 37](https://user-images.githubusercontent.com/75066505/102387556-cfface00-3fd0-11eb-830d-dce24ca7ded2.png)

## ClassDiagram

### Package Main
![Schermata 2020-12-23 alle 17 52 39](https://user-images.githubusercontent.com/75066505/103020235-57ec5500-4548-11eb-8c4c-8a45fec021eb.png)

### Package Model
![Schermata 2020-12-23 alle 17 53 15](https://user-images.githubusercontent.com/75066505/103020437-ab5ea300-4548-11eb-9f12-024db0a67abb.png)

### Package Filter
![Schermata 2020-12-30 alle 19 50 49](https://user-images.githubusercontent.com/75066505/103375044-67841480-4ad9-11eb-94ff-cca8c853bb99.png)

### Package Service
![Schermata 2021-01-04 alle 08 26 52](https://user-images.githubusercontent.com/75066505/103511229-e6c17180-4e66-11eb-8ad3-af0e69d064e0.png)

### Package Statistics
![Schermata 2020-12-31 alle 12 02 08](https://user-images.githubusercontent.com/75066505/103407865-6ba74500-4b60-11eb-80f7-e18568592a45.png)

### Package Utils
![utils](https://user-images.githubusercontent.com/75066510/103557783-003cda80-4eb4-11eb-9dab-bdfd2e47b7a8.png)

### Package Exception
![Schermata 2021-01-04 alle 08 48 19](https://user-images.githubusercontent.com/75066505/103512819-b9c28e00-4e69-11eb-919a-602b9b290dc6.png)

# Rotte Applicazione
Attraverso delle API REST (GET o POST) si possono eseguire delle richieste che 
differiscono in base alle diverse rotte mostrate nella tabella sottostante

TIPO | ROTTA | PARAMETRI | DESCRIZIONE
-----|-------|-----------|------------
GET  |/metadata | - | Restituisce i metadata 
POST |/stats | type, from, to | Effettua le statistiche relative ai parametri richiesti 
POST |/filters/cities | city, state | Restituisce le previsioni in base al nome della città e dello stato
POST |/filters/humidity | from, to | Restituisce le previsioni in base agli intervalli di umidità richiesti
POST |/filters/temperature | from, to | Restituisce le previsioni delle città in base agli intervalli di temperatura (kelvin) richiesti
POST |/filters/weather | weather, city | Restituisce le previsioni delle città in base ad una descrizione meteo desiderata

## Parametri delle statistiche
"Type" indica il parametro su cui si effettuano le statistiche, che possono perciò riguardate la temperatura o l'umidità
"From" indica  la data di inizio del periodo su cui si effettuano le statistiche
"to" indica la data di fine del periodo su cui si effettuano le statistiche

## Parametri filters/cities
"city" è il parametro che indica il nome della città
"state"  è il parametro che indica il codice dello stato in cui si trova la città

## Parametri filters/humidity
"from" indica il  primo valore  dell'intervallo preso in considerazione
"to" indica il valore finale dell'intervallo preso in considerazione

## Parametri filters/temperature
"from" indica il primo valore dell'intervallo preso in considera
"to" indica il valore finale dell'intervallo preso in considerazione




