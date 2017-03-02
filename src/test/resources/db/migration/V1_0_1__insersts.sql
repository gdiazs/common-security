insert into users (
					id,
					user_name,
					password,
					email,
					name,
					fathers_name,
					mothers_name,
					date_birth,
					account_non_expired,
					credentials_non_expired,
					account_non_locked,
					last_password_reset_date,
					enabled,
					created_at,
					updated_at )
values (1, 'admin', '$2a$06$Otz8Lg0p2sUqch5KVU7GX.AOdnMX0M8R3SbKk3DXORkNs7KChC9A.', 'diazgbs@gmail.com', 'Guillermo', 'Díaz', 'Solís', '1989-06-24', 1, 1, 1, '2015-07-07', 1, '2016-06-01', '2016-06-01');

insert into authorities (
							id,
							authority,
							enabled,
							created_at,
							updated_at
						)
values (1, 'ROLE_ADMIN', 1, '2016-06-01', '2016-06-01');

insert into authorities (
							id,
							authority,
							enabled,
							created_at,
							updated_at
						)
values (2, 'ROLE_MANAGER', 1, '2016-06-01', '2016-06-01');


insert into users_authorities(user_id, authority_id) values (1, 1);
insert into users_authorities(user_id, authority_id) values (1, 2);
