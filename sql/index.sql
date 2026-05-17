 create view view_artiste_artwork as select a.name as artiste_name, a.bio, aw.Title, aw.CreationYear, aw.Type, aw.Medium, aw.Dimensions, aw.Descriptions, aw.Price, aw.Status
 from Artiste a join Artwork aw on a.id_artiste = aw.id_artiste;

 create view view_exhibition_gallery as select g.name as nom_gallerie, g.address, e.title, e.theme
 from Gallery g join Exhibition e on g.idgallery = e.idgallery; 

create view view_nombre_exhibition as select g.name as nom_gallerie, count(e.idExibhition) as nombre_exhibition
from Gallery g join Exhibition e on g.idgallery = e.idgallery group by g.name; 

-- on choisit en index les id des tables principales 
create index idx_id_artiste on Artiste(id_artiste);
create index idx_idgallery on Gallery(idgallery);
create index idx_idExibhition on Exhibition(idExibhition);

select * from view_artiste_artwork;

select * from view_exhibition_gallery;

select * from view_nombre_exhibition; 