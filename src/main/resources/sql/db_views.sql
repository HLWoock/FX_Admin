CREATE VIEW `reservierung_per_klasse` 
AS SELECT stattauto_legacy.kfz.klasse, count(*) AS count
FROM stattauto_legacy.reservierung 
INNER JOIN stattauto_legacy.kfz 
ON stattauto_legacy.reservierung.kfz_id=stattauto_legacy.kfz.id
GROUP BY stattauto_legacy.kfz.klasse;