export class User {
    id: string;
    username: string;
    password: string;
    enabled: boolean;
    firstName: string;
    lastName: string;
    email: string;
    roles: string[] = [];
    authStatus: string;
    createdDate: Date;
    updateDate: Date;
}
