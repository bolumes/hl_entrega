HL_ENTREGA
O HL_ENTREGA é um aplicativo criado para atuar como intermediário entre usuários e restaurantes, oferecendo uma plataforma eficiente para pedidos de comida. O nome é uma homenagem aos criadores, Luis e Hamilton.

Estrutura do Aplicativo
O HL_ENTREGA é construído sobre uma base de dados SQLite chamada entrega.db, que contém cinco tabelas principais:

Usuário
Campos: id, nome_completo, telefone, email, endereço, senha
Admin
Campos: id, nome_completo, telefone, email, endereço, senha
Restaurante
Campos: id, nome_completo, telefone, email, endereço
Menu
Campos: id, data, descrição
Pedido
Campos: id, data, descrição, usuario_id
Segurança e Acesso
HL_ENTREGA implementa medidas de segurança robustas para proteger dados de acesso e informações pessoais. O aplicativo possui as seguintes funcionalidades de acesso:

Login

Permite a escolha entre duas opções: Admin ou Usuário.
Campos de entrada: e-mail e senha.
Opção para navegar para a tela de cadastro, caso o usuário ainda não tenha uma conta.
Cadastro (Signup)

Campos para registrar novos usuários.
Após o cadastro, o usuário pode ir diretamente para a tela de login.
Funcionalidades do Administrador (Admin)
A área administrativa do HL_ENTREGA fornece diversas ferramentas para gerenciar o sistema. As principais funcionalidades incluem:

Menu Definições
Permite atualizar e deletar informações de admins, usuários, restaurantes e menus.

Menu Perfil
Adição e listagem de administradores e usuários.

Menu GPS
Localização de clientes com base no número de telefone registrado.

Menu Câmera
Acesso à câmera para fins específicos, como verificação de identidade ou captura de imagens relevantes.

Menu Restaurantes
Adicionar novos restaurantes e visualizar a lista de restaurantes cadastrados.

Menu Menus
Adicionar o menu do dia e visualizar a lista de menus disponíveis.

Funcionalidades do Usuário
Os usuários do HL_ENTREGA têm acesso a uma interface simplificada para interagir com os serviços oferecidos. As principais funcionalidades incluem:

Menu do Dia
Visualização dos menus disponíveis para o dia.

Definições de Perfil
Opções para atualizar informações pessoais e visualizar detalhes do perfil.

Menu Câmera
Acesso à câmera do dispositivo.

Pedidos
Realizar pedidos de comida diretamente através do aplicativo.

Com esta estrutura clara, HL_ENTREGA se posiciona como uma solução eficaz para facilitar a interação entre usuários e restaurantes, proporcionando uma experiência de uso fluida e segura para todos os envolvidos.

