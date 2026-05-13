-- drop trigger checkDate;
-- drop trigger checkPlace;
-- drop trigger checkDuration;

delimiter //

-- Verification de la coherence des dates de creation des oeuvrespour eviter d'avoir une date de creation d'oeuvre avant la naissance de l'artiste
-- avec une erreur si la date n'est pas valide
create trigger checkDate before insert on artwork
for each row 
begin
select year(birth) from artiste where id_artiste=new.id_artiste into @artistYear;
if new.CreationYear< @artistYear then
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Artwork creation year older than artist birth year', MYSQL_ERRNO = 1001;
end if;
end; //

-- Verification que qu'un workshop n'est pas plein avec l'ajout d'une nouvelle reservation avec une erreur s'il n'y a plus de place
create trigger checkPlace before insert on booking
for each row
begin
select maxParticipant from workshop where idWorkshop=new.idWorkshop into @maxPlace;
select count(idMember) from booking where idWorkshop=new.idWorkshop and paymentStatus!='Cancelled' into @nbPlace;
if @maxPlace-@nbPlace<=0 then
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'No more place available', MYSQL_ERRNO = 1001;
end if;
end; //

-- Verification que la duree d'un workshop n'est pas negative lors de sa creation avec une erreur si c'est le cas
create trigger checkDuration before insert on workshop
for each row
begin
if new.duration<=0 then
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Negative duration', MYSQL_ERRNO = 1001;
end if;
end; //
delimiter ;


