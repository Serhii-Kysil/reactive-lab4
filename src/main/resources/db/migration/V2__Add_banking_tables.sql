-- Clients table
create table clients (
    id bigserial primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    email varchar(255) not null unique,
    phone_number varchar(20),
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

-- Accounts table
create table accounts (
    id bigserial primary key,
    account_number varchar(50) not null unique,
    balance decimal(15, 2) not null default 0.00,
    currency varchar(3) not null default 'UAH',
    is_blocked boolean not null default false,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);

-- Credit Cards table
create table credit_cards (
    id bigserial primary key,
    client_id bigint not null references clients(id) on delete cascade,
    account_id bigint not null references accounts(id) on delete cascade,
    card_number varchar(16) not null unique,
    card_holder_name varchar(200) not null,
    expiry_date date not null,
    cvv varchar(4) not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null default current_timestamp
);
