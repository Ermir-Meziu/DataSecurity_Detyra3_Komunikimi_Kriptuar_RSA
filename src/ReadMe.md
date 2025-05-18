# 🔐 Java Encrypted Chat - RSA Project

Ky projekt është një aplikacion **klient-server** i ndërtuar në Java, i cili lejon përdoruesit të komunikojnë në mënyrë të sigurt duke përdorur enkriptimin **RSA (asymmetric encryption)**. Të gjitha mesazhet që dërgohen midis klientëve janë të enkriptuara me çelësin publik të marrësit dhe mund të dekriptohen vetëm nga çelësi privat i tij.

## 📦 Paketimi

Të gjitha klasat ndodhen brenda paketës:
package Projekti;


## 📁 Skedarët kryesorë

| Skedari             | Përshkrimi                                                                 |
|---------------------|------------------------------------------------------------------------------|
| `RSAEncryption.java`| Krijon çiftin e çelësave (KeyPair) RSA dhe përmban metodat për enkriptim/dekriptim. |
| `ChatServer.java`   | Implementon serverin TCP që menaxhon lidhjet e klientëve dhe shpërndan çelësat publikë. |
| `ChatClient.java`   | Klienti që lidhet me serverin, komunikon me përdorues të tjerë dhe enkripton mesazhet. |

## ⚙️ Teknologjitë e përdorura

- Java SE 8 ose më i ri
- RSA Encryption (`javax.crypto`, `java.security`)
- Socket Programming (`java.net`)
- Multithreading

## 🚀 Si të ekzekutoni projektin

### 1. Startoni serverin

Ekzekutoni klasën `ChatServer.java`:

```bash
Run ChatServer.java