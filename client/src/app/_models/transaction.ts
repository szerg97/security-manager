import { Gsec } from "./gsec";

export interface Transaction extends Gsec{
    denomination: string;
    grossValue: string;
    netValue: string;
    yield: string;
    referenceYield: string;
    porfolioId?: string;
    issuerId: string;
    securityId: string;
}