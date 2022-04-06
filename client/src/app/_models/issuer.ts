import { Gsec } from "./gsec";

export interface Issuer extends Gsec{
    name: string;
    email: string;
    phone: string;
    status: boolean;
    addressId: string;
}