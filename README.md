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
![Schermata 2020-12-29 alle 13 03 42](https://user-images.githubusercontent.com/75066505/103282543-6ae2a780-49d6-11eb-87aa-2652c1d23b5e.png)

### Package Statistics
![Schermata 2020-12-31 alle 12 02 08](https://user-images.githubusercontent.com/75066505/103407865-6ba74500-4b60-11eb-80f7-e18568592a45.png)

### Package Utils
![Schermata 2020-12-31 alle 11 41 12](https://user-images.githubusercontent.com/75066505/103407810-4581a500-4b60-11eb-970e-e761b444123d.png)

### Package Exception
![Schermata 2020-12-30 alle 19 56 27](https://user-images.githubusercontent.com/75066505/103375000-3c012a00-4ad9-11eb-9eef-96c2fb775a93.png)

# Rotte Applicazione
Attraverso delle API REST (GET o POST) si possono eseguire delle richieste che 
differiscono in base alle diverse rotte mostrate nella tabella sottostante


