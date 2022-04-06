import { Gsec } from "./gsec";

export interface Customer extends Gsec{
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    idCard: string;
    dateOfBirth: Date;
    gender: boolean;
    addressId: string;
}